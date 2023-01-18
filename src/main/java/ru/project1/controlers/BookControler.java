package ru.project1.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.project1.Dao.BookDAO;
import ru.project1.Models.Book;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
@ComponentScan("src.webapp.WEB-INF.views.Books")
public class BookControler {
    private final BookDAO bookDAO;

    @Autowired
    public BookControler(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "Books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "Books/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "Books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }
    /*
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", bookDAO.show(id));
        return "Person/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", bookDAO.show(id));
        return "Person/edit";
    }

*/

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "Books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
