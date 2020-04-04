/*

    ItemManager - A plugin to manage items in player's inventory.
    Copyright (C) 2019 aBooDyy

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

package net.aboodyy.itemmanager;

public class ItemWrapper {

    private String mat, nameSW, nameE, nameER, nameC, nameCR, nameEW, loreSW, loreE, loreC, loreCR, loreEW;
    private String[] enchs;
    private int data = 0, amt = 1;
    private boolean strict = false;

    public String getMat() {
        return mat;
    }

    public int getData() {
        return data;
    }

    public int getAmt() {
        return amt;
    }

    public String getNameSW() {
        return nameSW;
    }

    public String getNameE() {
        return nameE;
    }

    public String getNameER() {
        return nameER;
    }

    public String getNameC() {
        return nameC;
    }

    public String getNameCR() {
        return nameCR;
    }

    public String getNameEW() {
        return nameEW;
    }

    public String getLoreSW() {
        return loreSW;
    }

    public String getLoreE() {
        return loreE;
    }

    public String getLoreC() {
        return loreC;
    }

    public String getLoreCR() {
        return loreCR;
    }

    public String getLoreEW() {
        return loreEW;
    }

    public String[] getEnchs() {
        return enchs;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public void setNameSW(String nameSW) {
        this.nameSW = nameSW;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    public void setNameER(String nameER) {
        this.nameER = nameER;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public void setNameCR(String nameCR) {
        this.nameCR = nameCR;
    }

    public void setNameEW(String nameEW) {
        this.nameEW = nameEW;
    }

    public void setLoreSW(String loreSW) {
        this.loreSW = loreSW;
    }

    public void setLoreE(String loreE) {
        this.loreE = loreE;
    }

    public void setLoreC(String loreC) {
        this.loreC = loreC;
    }

    public void setLoreCR(String loreCR) {
        this.loreCR = loreCR;
    }

    public void setLoreEW(String loreEW) {
        this.loreEW = loreEW;
    }

    public void setEnchs(String[] enchs) {
        this.enchs = enchs;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
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
