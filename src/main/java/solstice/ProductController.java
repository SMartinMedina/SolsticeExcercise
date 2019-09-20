package solstice;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /* PUNTO UNO - ADD PRODUCT - POST*/
    @PostMapping("/products")
    public ResponseEntity products(@RequestParam long product_id, @RequestParam String name, @RequestParam String category, @RequestParam Double retail_price, @RequestParam Double discounted_price, @RequestParam boolean availability) {
        List<Producto> productos = getAllProductsDraft();
        Producto producto = new Producto(product_id,name,category, retail_price, discounted_price,availability);
        if(!existById(product_id)) {
            productos.add(producto);
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
    /* PUNTO DOS - UPDATE PRODUCT - PUT*/
    @PutMapping("/products/{product_id}")
    public ResponseEntity updateProduct(@PathVariable long product_id,
                                        @RequestParam String name,
                                        @RequestParam String category,
                                        @RequestParam(value="retail_price", required=false) Double retail_price,
                                        @RequestParam(value="discounted_price", required=false)  Double discounted_price,
                                        @RequestParam(value="availability", required=false)  boolean availability) {
        List<Producto> productos = getAllProductsDraft();
        if(!existById(product_id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else{
            Producto producto = getProductById(product_id);
            try {
                producto.setName(name);
                producto.setCategory(category);
                if(retail_price != null){
                    producto.setRetail_price(retail_price);
                }
                if(discounted_price != null){
                    producto.setDiscounted_price(discounted_price);
                }
                if(availability){
                    producto.setAvailability(availability);
                }
                return new ResponseEntity(HttpStatus.OK);
            }catch (NullPointerException n){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

        }
    }
    /* PUNTO TRES - GET PRODUCT - GET*/
    @GetMapping("/products/{product_id}")
    public ResponseEntity getProduct(@RequestParam long product_id) {

        Producto producto = getProductById(product_id);
        if(producto!=null){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    /* PUNTO CUATRO - RETURN BY CATEGORY - GET*/
    @GetMapping("/products")
    public ResponseEntity getProductByCategory(@RequestParam(value="category") String category) {
        List<Producto> foundedProducts = findProductByCategory(getAllProductsDraft(),category);
        if(foundedProducts != null){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
    /* PUNTO CINCO - RETURN BY CATEGORY AND AVAILABILITY - GET*/
    @RequestMapping("/products/{product_id}")
    public ResponseEntity getProductByCategoryAndAvailability(@RequestParam(value="category") String category, @RequestParam(value="availability") boolean availability) {
        List<Producto> foundedProducts = findProductByCategoryAndAvailability(getAllProductsDraft(),category);
        if(foundedProducts != null){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    /* PUNTO SEIS - RETURN ALL PRODUCTS - GET*/
    @RequestMapping("/products")
    public List<Producto> getAllProduct() {
        return getAllProductsDraft();
    }

    public List<Producto> getAllProductsDraft(){
        List<Producto> productos = new ArrayList<Producto>();
        productos.add(new Producto(1L, "Martin", "Categoria Uno", 100.00, 10.00, true));
        productos.add(new Producto(15L, "Luis", "Categoria Dos", 110.00, 11.00, false));
        productos.add(new Producto(12L, "Alberto", "Categoria Uno", 150.00, 15.00, false));
        productos.add(new Producto(14L, "Dante", "Categoria Uno", 80.00, 8.00, true));
        productos.add(new Producto(10L, "Fabio", "Categoria Dos", 10.00, 10.00, false));
        productos.add(new Producto(9L, "Karina", "Categoria Uno", 190.00, 19.00, true));
        productos.add(new Producto(8L, "Carla", "Categoria Uno", 130.00, 13.00, false));
        productos.add(new Producto(5L, "Romina", "Categoria Uno", 100.00, 10.00, true));
        productos.add(new Producto(6L, "Karen", "Categoria Dos", 95.00, 95.00, false));
        productos.add(new Producto(4L, "Laura", "Categoria Uno", 105.00, 10.50, false));
        productos.add(new Producto(3L, "Lorenzo", "Categoria Tres", 135.00, 13.50, false));
        productos.add(new Producto(13L, "Nito", "Categoria Uno", 45.00, 4.50, false));
        productos.add(new Producto(2L, "Patricio", "Categoria Dos", 5.00, 0.50, true));
        productos.add(new Producto(1L, "Ruben", "Categoria Uno", 350.00, 35.00, true));
        productos.add(new Producto(11L, "Marcos", "Categoria Tres", 11.00, 1.10, false));
        productos.add(new Producto(7L, "Nancy", "Categoria Tres", 9.00, 0.90, true));
        return productos;
    }
    public boolean existById(long id){
        List<Producto> productos = getAllProductsDraft();
        boolean ret = false;
        for(Producto prod : productos){
            if(prod.getId() == id){
                ret = true;
                break;
            }
        }
        return ret;
    }
    public Producto getProductById(long id){
        List<Producto> productos = getAllProductsDraft();
        Producto ret = null;
        for(Producto prod : productos) {
            if (prod.getId() == id) {
                ret = prod;
                break;
            }
        }
        return ret;
    }
    public List<Producto> findProductByCategory(List<Producto> productos, String category){
        List<Producto> ret = new ArrayList<Producto>();
        for(Producto prod : productos) {
            if (prod.getCategory().equalsIgnoreCase(category)) {
                ret.add(prod);
            }
        }
        return ret;
    }
    public List<Producto> findProductByCategoryAndAvailability(List<Producto> productos, String category){
        List<Producto> ret = new ArrayList<Producto>();
        for(Producto prod : productos) {
            if (prod.getCategory().equalsIgnoreCase(category) && prod.isAvailability()) {
                ret.add(prod);
            }
        }
        return ret;
    }
}
