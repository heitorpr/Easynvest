package br.heitor.easyinvest.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.InflateException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FontManager {

    // Different tags used in XML file.
    private static final String TAG_FAMILY = "family";
    private static final String TAG_NAMESET = "nameset";
    private static final String TAG_NAME = "name";
    private static final String TAG_FILESET = "fileset";
    private static final String TAG_FILE = "file";

    // Different styles supported.
    private static final String STYLE_BLACK = "-Black.otf";
    private static final String STYLE_BOLD = "-Bold.otf";
    private static final String STYLE_LIGHT = "-Light.otf";
    private static final String STYLE_MEDIUM = "-Medium.otf";
    private static final String STYLE_REGULAR = "-Regular.otf";

    private List<Font> mFonts;
    //private boolean isFamilySet = false;
    private boolean isName = false;
    private boolean isFile = false;

    private FontManager() {
    }

    public static FontManager getInstance() {
        return FontManager.InstanceHolder.INSTANCE;
    }

    // Parse the resId and initialize the parser.
    public void initialize(Context context, int resId) {
        XmlResourceParser parser = null;
        try {
            parser = context.getResources().getXml(resId);
            mFonts = new ArrayList<>();

            String tag;
            int eventType = parser.getEventType();

            Font font = null;

            do {
                tag = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        font = getFontFromStartTag(tag, font);
                        break;

                    case XmlPullParser.END_TAG:
                        font = getFontFromEndTag(tag, font);
                        break;

                    case XmlPullParser.TEXT:
                        parseFontText(context, parser, font);
                }

                eventType = parser.next();
            } while (eventType != XmlPullParser.END_DOCUMENT);

        } catch (XmlPullParserException | IOException e) {
            throw new InflateException("Error inflating font XML", e);
        } finally {
            if (parser != null) parser.close();
        }
    }

    private void parseFontText(Context context, XmlResourceParser parser, Font font) {
        String text = parser.getText();
        if (isName) {
            // value is a name, add it to list of family-names.
            if (font != null) {
                if (font.families != null)
                    font.families.add(text);
            }
            return;
        }

        if (!isFile) return;

        // value is a file, add it to the proper kind.
        FontStyle fontStyle = new FontStyle();
        fontStyle.font = Typeface.createFromAsset(context.getAssets(), text);

        if (text.endsWith(STYLE_BLACK))
            fontStyle.style = FontStyleEnum.BLACK;
        else if (text.endsWith(STYLE_BOLD))
            fontStyle.style = FontStyleEnum.BOLD;
        else if (text.endsWith(STYLE_LIGHT))
            fontStyle.style = FontStyleEnum.LIGHT;
        else if (text.endsWith(STYLE_MEDIUM))
            fontStyle.style = FontStyleEnum.MEDIUM;
        else
            fontStyle.style = FontStyleEnum.REGULAR;

        if (font == null) return;
        font.styles.add(fontStyle);
    }

    @Nullable
    private Font getFontFromEndTag(String tag, Font font) {
        switch (tag) {
            case TAG_FAMILY:
                // add it to the list.
                if (font != null) {
                    mFonts.add(font);
                    font = null;
                }
                break;
            case TAG_NAME:
                isName = false;
                break;
            case TAG_FILE:
                isFile = false;
                break;
        }

        return font;
    }

    @Nullable
    private Font getFontFromStartTag(String tag, Font font) {
        switch (tag) {
            case TAG_FAMILY:
                // one of the font-families.
                font = new Font();
                break;
            case TAG_NAMESET:
                // a list of font-family names supported.
                if (font != null) {
                    font.families = new ArrayList<>();
                }
                break;
            case TAG_NAME:
                isName = true;
                break;
            case TAG_FILESET:
                // a list of files specifying the different styles.
                if (font != null) {
                    font.styles = new ArrayList<>();
                }
                break;
            case TAG_FILE:
                isFile = true;
                break;
        }

        return font;
    }

    public Typeface get(String family, FontStyleEnum style) {
        for (Font font : mFonts) {
            for (String familyName : font.families) {
                if (!familyName.equals(family)) continue;

                for (FontStyle fontStyle : font.styles) {
                    if (fontStyle.style != style) continue;
                    return fontStyle.font;
                }
            }
        }

        return null;
    }

    public enum FontStyleEnum {
        REGULAR(0), BLACK(1), BOLD(2), LIGHT(3), MEDIUM(4);
        int id;

        FontStyleEnum(int id) {
            this.id = id;
        }

        public static FontStyleEnum fromId(int id) {
            for (FontStyleEnum f : values()) {
                if (f.id == id) return f;
            }
            throw new IllegalArgumentException();
        }
        }

    //Making FontManager a singleton class
    private static class InstanceHolder {
        private static final FontManager INSTANCE = new FontManager();
    }

    private class FontStyle {
        FontStyleEnum style;
        Typeface font;
    }

    private class Font {
        // different font-family names that this Font will respond to.
        List<String> families;

        // different styles for this font.
        List<FontStyle> styles;
    }
}
