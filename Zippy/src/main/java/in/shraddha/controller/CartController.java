package in.shraddha.controller;

import in.shraddha.repository.ProductRepo;
import in.shraddha.entity.Cart;
import in.shraddha.entity.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CartController {

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/cart/add/{id}")
    @ResponseBody
    public String addToCart(@PathVariable Integer id, HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        Product product = productRepo.findById(id).orElse(null);
        if (product == null) return "Product not found";

        Cart item = cart.get(id);
        if (item == null) {
            item = new Cart(id, product.getPname(), product.getPimg(), product.getPprice(), 1, product.getPdiscount());
        } else {
            if (item.getQuantity() < 10) {
                item.setQuantity(item.getQuantity() + 1);
            }
        }

        cart.put(id, item);
        session.setAttribute("cart", cart);

        return "Added";  // Change button to "Saved to Cart" on success
    }

    @PostMapping("/cart/update/{id}")
    @ResponseBody
    public String updateQuantity(@PathVariable Integer id,
                                 @RequestParam("action") String action,
                                 HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart == null) return "No cart found";

        Cart item = cart.get(id);
        if (item == null) return "Item not found";

        if ("inc".equals(action) && item.getQuantity() < 10) {
            item.setQuantity(item.getQuantity() + 1);
        } else if ("dec".equals(action)) {
            item.setQuantity(item.getQuantity() - 1);
            if (item.getQuantity() <= 0) cart.remove(id);
        }

        session.setAttribute("cart", cart);
        return "Updated";  // Cart quantity updated
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        model.addAttribute("cartItems", cart != null ? cart.values() : Collections.emptyList());

        double total = 0;
        if (cart != null) {
            for (Cart item : cart.values()) {
                total += item.getTotalPrice(); // Calculate total using discounted price
            }
        }

        model.addAttribute("total", total);
        return "cart"; // Display the cart view with the total
    }

    @GetMapping("/cart/count")
    @ResponseBody
    public int getCartCount(HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        return cart != null ? cart.values().stream().mapToInt(Cart::getQuantity).sum() : 0;
    }

    @PostMapping("/cart/increase/{productId}")
    public String increaseQuantity(@PathVariable int productId, HttpSession session,Model model) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart != null && cart.containsKey(productId)) {
            Cart item = cart.get(productId);
            int currentQty = item.getQuantity();
            if (currentQty < 10) {
                item.setQuantity(currentQty + 1);
            }
        }
        return "redirect:/cart"; // Redirect to cart page after increasing quantity
    }


    @PostMapping("/cart/decrease/{productId}")
    public String decreaseQuantity(@PathVariable int productId, HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart != null && cart.containsKey(productId)) {
            Cart item = cart.get(productId);
            int qty = item.getQuantity();
            if (qty > 1) {
                item.setQuantity(qty - 1);
            } else {
                cart.remove(productId);
            }
        }
        return "redirect:/cart"; // Redirect to cart page after decreasing quantity
    }

    @PostMapping("/cart/remove/{productId}")
    public String removeItem(@PathVariable int productId, HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart != null) {
            cart.remove(productId); // Remove item from cart
        }
        return "redirect:/cart"; // Redirect to cart page after removal
    }
}