/*
 * Copyright (C) 2023 aksha
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package labs.pm.data;

import java.math.BigDecimal;
import static java.math.RoundingMode.*; 
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author aksha
 */
public abstract class Product {

    private final int id;
    private final String name;
    private final BigDecimal price;
    private final Rating rating;
    
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    

    public Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
    
    public Product(int id, String name, BigDecimal price) {
        this(id,name,price,Rating.NOT_RATED);
    }

    public Product() {
        this(0,"no name",BigDecimal.ZERO);
    }
    
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

   public BigDecimal getDiscount()
    {
        return price.multiply(DISCOUNT_RATE).setScale(2,HALF_UP);
    }
    
    public Rating getRating()
    {
        return rating;
    }
    
    public abstract Product applyRating(Rating newRating);

    @Override
    public String toString() {
        return  id + ", " + name + ", " + price + ", " + getDiscount()+", " + rating.getStars()+" "+getBestBefore();
    }
    
    /**
     * Get the value of bestBefore
     *
     * @return the value of bestBefore
     */
    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if(obj instanceof Product){
            final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;}
        return false;
    }
    
}
