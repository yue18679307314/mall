package com.yunxin.cb.mall.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeViewItem {
    private String id;
    private String text;
    private Boolean expanded;
    private String spriteCssClass;
    private boolean hasChildren;
    private boolean checked = true;
    private List<TreeViewItem> items;

    public TreeViewItem() {
    }

    public TreeViewItem(String id, String text, Boolean expanded, String spriteCssClass, boolean hasChildren, boolean checked) {
        this.id = id;
        this.text = text;
        this.expanded = expanded;
        this.spriteCssClass = spriteCssClass;
        this.hasChildren = hasChildren;
        this.checked = checked;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public Boolean getExpanded() {
        return this.expanded;
    }

    public void setExpanded(Boolean value) {
        this.expanded = value;
    }

    public String getSpriteCssClass() {
        return this.spriteCssClass;
    }

    public void setSpriteCssClass(String value) {
        this.spriteCssClass = value;
    }

    public List<TreeViewItem> getItems() {
        if (this.items == null) {
            this.items = new ArrayList();
        }

        return this.items;
    }

    public void setFields(String id, String text, String spriteCssClass, Boolean expanded) {
        this.setId(id);
        this.setText(text);
        this.setSpriteCssClass(spriteCssClass);
        this.setExpanded(expanded);
    }

    public void setItems(List<TreeViewItem> items) {
        this.items = items;
    }

    public boolean isHasChildren() {
        return this.hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean selected) {
        this.checked = selected;
    }

}