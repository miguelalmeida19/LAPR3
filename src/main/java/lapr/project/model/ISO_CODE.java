package lapr.project.model;

public enum ISO_CODE {
    GENERAL_PROPOSE("22GP", 6.0960, 2.4384, 2.5908),
    VENTILATED("22VH", 6.0960, 2.4384, 2.5908),
    OPEN_TOP("22UT", 6.0960, 2.4384, 2.5908),
    HARD_TOP("22UP", 6.0960, 2.4384, 2.5908),
    REFRIGERATED("22RT", 6.0960, 2.4384, 2.5908),
    TANK_DANGEROUS_LIQUIDS("20TD", 6.0960, 2.4384, 2.4384),
    HIGH_CUBE_GP("45GP", 12.1920, 2.4384, 2.8956),
    FLAT("42PF", 12.1920, 2.4384, 2.5908);

    /**
     * Constructor
     */
    ISO_CODE(String code, double sizeX, double sizeY, double sizeZ) {
        this.code = code;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;

    }

    public double sizeX;
    public double sizeY;
    public double sizeZ;
    public String code;

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }
    /**
     * @return x size
     */
    public double getSizeX() {
        return sizeX;
    }
    /**
     * @return y size
     */
    public double getSizeY() {
        return sizeY;
    }
    /**
     * @return z size
     */
    public double getSizeZ() {
        return sizeZ;
    }

    /**
     * @return the isoCode by the code
     */
    public static ISO_CODE fromString(String text) {
        for (ISO_CODE b : ISO_CODE.values()) {
            if (b.getCode().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
