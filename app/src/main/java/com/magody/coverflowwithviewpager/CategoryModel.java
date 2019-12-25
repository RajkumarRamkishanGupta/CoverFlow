package com.magody.coverflowwithviewpager;

class CategoryModel implements Comparable {
    String name;
    String id;
    boolean selected;

    public CategoryModel(String name, String id, boolean selected) {
        this.name = name;
        this.id = id;
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int compareTo(Object o) {
            CategoryModel compare = (CategoryModel) o;

            if (compare.getName().equals(this.name)){
                return 0;
            }
            return 1;
        }
    }
