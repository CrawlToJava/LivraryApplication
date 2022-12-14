package application.controller;

import application.entity.Book;
import application.exception.NoDataFoundException;
import application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        System.out.println(bookService.findAll());
        return "books/findAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElseThrow(() -> new NoDataFoundException("Book does not exist")));
        return "books/findById";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/save";
        bookService.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/update";
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String saveView(Model model) {
        model.addAttribute(new Book());
        return "books/save";
    }

    @GetMapping("/{id}/update")
    public String updateView(Model model, @PathVariable("id") Long id) {
        model.addAttribute("book", bookService.findById(id).orElseThrow(() -> new NoDataFoundException("Book does not exist")));
        return "books/update";
    }
}
