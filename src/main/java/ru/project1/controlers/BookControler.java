package ru.project1.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.project1.Models.Book;
import ru.project1.service.BookService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/books")
@ComponentScan("src.webapp.WEB-INF.views.Books")
public class BookControler {

private final BookService bookService;


    @Autowired
    public BookControler( BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model,@RequestParam (value = "page",defaultValue = "0") Integer offset,
                        @RequestParam(value = "books_per_page",defaultValue = "20") Integer limit,@RequestParam(value = "sort_by_year",defaultValue = "false")boolean sort){
        if(offset!=null && limit!=null){
            model.addAttribute("books", bookService.index(offset,limit));
        }
        if(sort) model.addAttribute("books", bookService.indexs());
        if(offset!=null && limit!=null && sort){
            model.addAttribute("books", bookService.indexOfEach(offset,limit));
        }
        else {
            model.addAttribute("books", bookService.index());
        }
        return "Books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "Books/new";
    }
    @PostMapping("/{id}/create")
    public String create(@RequestParam("example")String ex,@PathVariable("id") int id){
    bookService.setBook(id, Integer.parseInt(ex));

        return "redirect:/books/{id}";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "Books/new";

       bookService.save(book);
        return "redirect:/books";
    }
    @PostMapping("/{id}/release_the_book")
    public String release(@PathVariable("id") int id){
        bookService.setBook(id,null);
        return "redirect:/books/{id}";
    }
   @GetMapping("/search")
    public String pageOfSearch(){
    return "Books/search";
    }
    @PostMapping("/search")
    public String search(@ModelAttribute("sea") String search,Model model){
        model.addAttribute("result",bookService.findByTitleStartingWith(search));
        return "Books/search";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        if(!Objects.equals(bookService.getIdOwner(id), null)) {
            model.addAttribute("FIO", bookService.getFIO(id));
        }
        model.addAttribute("allPeople",bookService.findAllPeoples());
        return "Books/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("currentBook", bookService.findById(id));
        return "Books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "Books/edit";

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
       bookService.delete(id);
        return "redirect:/books";
    }
}
