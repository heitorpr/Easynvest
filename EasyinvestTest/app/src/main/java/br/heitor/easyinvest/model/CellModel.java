package br.heitor.easyinvest.model;

public class CellModel {
    private int id;
    private int type;
    private String message;
    private String typefield;
    private boolean hidden;
    private double topSpacing;
    private Integer show;
    private boolean required;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypefield() {
        return this.typefield;
    }

    public void setTypefield(String typefield) {
        this.typefield = typefield;
    }

    public boolean getHidden() {
        return this.hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public double getTopSpacing() {
        return this.topSpacing;
    }

    public void setTopSpacing(double topSpacing) {
        this.topSpacing = topSpacing;
    }

    public Integer getShow() {
        return this.show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public boolean getRequired() {
        return this.required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

//    Enum Type {  case field = 1,  case text = 2,  case image = 3,  case checkbox = 4,  case send = 5 }
//    Enum TypeField {  case text = 1,  case telNumber = 2,  case email = 3 }
}