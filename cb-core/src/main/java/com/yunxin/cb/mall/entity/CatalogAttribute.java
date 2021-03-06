/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TblBaseSystemUser generated by hbm2java
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class CatalogAttribute implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1493805487205809206L;

    /**
     * 属性ID
     */

    private int attributeId;

    /**
     * 属性组
     */

    private CatalogAttributeGroup catalogAttributeGroup;

    /**
     * 属性名
     */

    private String attributeName;

    /**
     * 属性图片路径
     */

    private String imagePath;

    /**
     * 排序
     */
    private short sortOrder;

    public CatalogAttribute() {

    }

    public CatalogAttribute(int attributeId) {
        this.attributeId = attributeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", nullable = false)
    @JsonIgnore
    public CatalogAttributeGroup getCatalogAttributeGroup() {
        return catalogAttributeGroup;
    }

    public void setCatalogAttributeGroup(CatalogAttributeGroup attributeGroup) {
        this.catalogAttributeGroup = attributeGroup;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int propId) {
        this.attributeId = propId;
    }

    @Column(nullable = false, length = 32)
    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String propName) {
        this.attributeName = propName;
    }

    @Column(nullable = true, length = 255)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(nullable = true, length = 255)


    public short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(short sortOrder) {
        this.sortOrder = sortOrder;
    }
}
