package ru.project1.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.project1.Dao.BookDAO;
import ru.project1.Dao.PersonDAO;
import ru.project1.Models.Book;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/books")
@ComponentScan("src.webapp.WEB-INF.views.Books")
public class BookControler {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public BookControler(BookDAO bookDAO,PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO=personDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books",bookDAO.index());
        return "Books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "Books/new";
    }
    @PostMapping("/{id}/create")
    public String create(@RequestParam("example")String ex,@PathVariable("id") int id){
    bookDAO.setBook(id, Integer.parseInt(ex));
        return "redirect:/books/{id}";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "Books/new";

       bookDAO.save(book);
        return "redirect:/books";
    }
    @PostMapping("/{id}/release_the_book")
    public String release(@PathVariable("id") int id){
        bookDAO.setBook(id,null);
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        if(!Objects.equals(bookDAO.show(id).getId_person(), null)) {
            model.addAttribute("FIO", personDAO.show(Integer.parseInt(bookDAO.show(id).getId_person())).getFIO());
        }
        model.addAttribute("allPeople",personDAO.index());
        return "Books/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "Books/edit";
    }

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
