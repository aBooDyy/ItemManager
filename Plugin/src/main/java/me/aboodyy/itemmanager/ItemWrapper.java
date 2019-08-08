package me.aboodyy.itemmanager;

public class ItemWrapper {

    public String mat, nameSW, nameE, nameER, nameC, nameCR, nameEW, loreSW, loreE, loreC, loreCR, loreEW;
    public String[] enchs;
    public int data, amt;
    public boolean strict;

    public ItemWrapper(String material, int data, int amount, String nameStartsWith, String nameEquals,
                       String nameEqualsRegex, String nameContains, String nameContainsRegex, String nameEndsWith,
                       String loreStartsWith, String loreEquals, String loreContains,
                       String loreContainsRegex, String loreEndsWith, String[] enchantments , boolean isStrict) {
        mat = material;
        this.data = data;
        amt = amount;
        nameSW = nameStartsWith;
        nameE = nameEquals;
        nameER = nameEqualsRegex;
        nameC = nameContains;
        nameCR = nameContainsRegex;
        nameEW = nameEndsWith;
        loreSW = loreStartsWith;
        loreE = loreEquals;
        loreC = loreContains;
        loreCR = loreContainsRegex;
        loreEW = loreEndsWith;
        enchs = enchantments;
        strict = isStrict;
    }

    public boolean matExists() {
        return mat != null;
    }

    public boolean nameSWExists() {
        return nameSW != null;
    }

    public boolean nameEExists() {
        return nameE != null;
    }

    public boolean nameERExists() {
        return nameER != null;
    }

    public boolean nameCExists() {
        return nameC != null;
    }

    public boolean nameCRExists() {
        return nameCR != null;
    }

    public boolean nameEWExists() {
        return nameEW != null;
    }

    public boolean loreSWExists() {
        return loreSW != null;
    }

    public boolean loreEExists() {
        return loreE != null;
    }

    public boolean loreCExists() {
        return loreC != null;
    }

    public boolean loreCRExists() {
        return loreCR != null;
    }

    public boolean loreEWExists() {
        return loreEW != null;
    }

    public boolean enchsExist() {
        return enchs != null;
    }

}
