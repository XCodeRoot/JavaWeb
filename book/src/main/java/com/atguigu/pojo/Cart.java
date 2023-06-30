package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *  Cart表示购物车,购物车有三个内容
 *  分别是 总数,总价,商品项集合
 */
public class Cart {
    //private Integer totalCount;//总数
    //private BigDecimal totalPrice;//总价

    //key是商品编号,value是商品信息
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();//购物车的商品项集合

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());//通过key来查找value

        if(item==null){//如果这个商品 每出现在购物车里,则放进购物车
            items.put(cartItem.getId(),cartItem);
        }else{//如果这个商品已经出现在购物车了,则直接加数量
            item.setCount(item.getCount()+1);//数量累计
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改购物车商品数量
     * @param id
     */
    public void updateCount(Integer id,Integer count){
        //先查看购物车中是否有此商品,如果有,则更新此商品数量和总金额
        CartItem item = items.get(id);
        if(item!=null){//有
            item.setCount(count);//更新数量
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }


    }

    //无构造器,因为用不到

    public Integer getTotalCount() {
        Integer totalCount=0;
        // items.entrySet()取出所有 键值对, 遍历所有键值对
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }


        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem>entry : items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
