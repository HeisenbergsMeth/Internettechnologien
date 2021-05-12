package de.falkmarinov.Internettechnologien.model;

import de.falkmarinov.Internettechnologien.repository.Dao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;
import java.io.Serializable;
import java.util.*;

import static java.util.Map.Entry;

@Named("shoppingCart")
@SessionScoped
public class ShoppingCart implements Serializable {

    @Inject
    @Named("bookDao")
    private Dao<Book> bookDao;

    private Long lastInsertedId;

    private Double total = 0.0;

    private final Map<Long, Integer> positions = new HashMap<>();

    public void insertBook(Long id) {
        if (!positions.containsKey(id)) {
            positions.put(id, 1);
            increaseTotal(id);
        }
    }

    public void increaseAmount(Long id) {
        if (positions.containsKey(id)) {
            Integer amount = positions.get(id);
            positions.put(id, amount + 1);
            increaseTotal(id);
        }
    }

    public void decreaseAmount(Long id) {
        if (positions.containsKey(id) && positions.get(id) > 1) {
            Integer amount = positions.get(id);
            positions.put(id, amount - 1);
        } else {
            positions.remove(id);
        }
        decreaseTotal(id);
    }

    public List<Entry<Long, Integer>> getPositionEntryList() {
        return new ArrayList<>(positions.entrySet());
    }

    public Book getBook(Long id) {
        Optional<Book> optionalBook = bookDao.get(id);
        return optionalBook.orElseThrow(NotFoundException::new);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getLastInsertedId() {
        return lastInsertedId;
    }

    public void setLastInsertedId(Long lastInsertedId) {
        this.lastInsertedId = lastInsertedId;
        insertBook(lastInsertedId);
    }

    private void increaseTotal(Long id) {
        Book book = getBook(id);
        total += book.getPrice();
    }

    private void decreaseTotal(Long id) {
        Book book = getBook(id);
        total -= book.getPrice();
    }
}
