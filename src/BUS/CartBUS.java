package BUS;

import DTO.CartItemDTO;
import java.util.ArrayList;
import java.util.List;

public class CartBUS {

    private static List<CartItemDTO> cartList = new ArrayList<>();

    public static void addToCart(CartItemDTO item){

        for(CartItemDTO cart : cartList){
            if(cart.getMaSP().equals(item.getMaSP()) &&
               cart.getSize().equals(item.getSize())){

                cart.tangSoLuong();
                return;
            }
        }

        cartList.add(item);
    }
    public static void removeItem(String maSP, String size){

        for(int i = 0; i < cartList.size(); i++){

            CartItemDTO item = cartList.get(i);

            if(item.getMaSP().equals(maSP) &&
               item.getSize().equals(size)){

                cartList.remove(i);
                return;
            }
        }
    }
    public static List<CartItemDTO> getCart(){
        return cartList;
    }

    public static void deductQuantity(String maSP, String size){

        for(CartItemDTO cart : cartList){

            if(cart.getMaSP().equals(maSP) &&
               cart.getSize().equals(size)){

                if(cart.getSoLuong() == 1){
                    cartList.remove(cart);
                }
                else{
                    cart.giamSoLuong();
                }

                return;
            }
        }
    }

    public static void addQuantity(String maSP, String size) {
        for(CartItemDTO cart : cartList){
            if(cart.getMaSP().equals(maSP) &&
               cart.getSize().equals(size)){

                cart.tangSoLuong();
                return;
            }
        }
    }
    
    public static double getTotalPrice(){

        double total = 0;

        for(CartItemDTO item : cartList){
            total += item.getGia() * item.getSoLuong();
        }

        return total;
    }
    
    public static int getQuantityByMaSP(String maSP){

        int total = 0;

        for(CartItemDTO item : cartList){
            if(item.getMaSP().equals(maSP)){
                total += item.getSoLuong();
            }
        }

        return total;
    }
    public static void clearCart(){
        cartList.clear();
    }
}