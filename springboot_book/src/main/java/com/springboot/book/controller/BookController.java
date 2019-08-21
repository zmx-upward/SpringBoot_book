package com.springboot.book.controller;

import com.springboot.book.entity.Book;
import com.springboot.book.mapper.BookMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookMapper bookMapper;

    /**
     * 查询
     */
//    @RequestMapping("/list")
//    public String listAll(Model model){
//        List<Book> book = bookMapper.listAll();
//        model.addAttribute("list",book);
//        return "list";
//    }
    @GetMapping("/list")
    public ModelAndView list(Model model) {
        model.addAttribute("bookList",bookMapper.listAll());
        model.addAttribute("title", "所有书籍");
        return new ModelAndView("list","bookModel",model);
    }

    /**
     * 根据id查询
     * @return
     */
//    @RequestMapping("/getBook/{id}")
//    public String getBook(int id, HttpServletRequest request, Model model){
//        request.setAttribute("book", bookMapper.findById(id));
//        model.addAttribute("book", bookMapper.findById(id));
//        return "/update";
//    }
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Integer id, Model model) {
        Book book = bookMapper.findById(id);
        model.addAttribute("book",book);
        model.addAttribute("title", "查看书籍");
        return new ModelAndView("view","bookModel",model);
    }

    /**
     * 获取表单页面
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("book",new Book());
        model.addAttribute("title", "创建书籍");
        return new ModelAndView("form","bookModel",model);
    }

    /**
     * 添加操作
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insert(HttpServletRequest request, HttpSession session, String name, Integer num){
        if (name != null && num != null) {
            Book book = new Book();
            book.setName(name);
            book.setNum(num);
            bookMapper.insert(book);
        }
        ModelAndView mv = new ModelAndView("insert");
        return mv;
    }

    /**
     *编辑
     */
    @RequestMapping("/update")
    public String updateBook(Book book, HttpServletRequest request){
        bookMapper.update(book);
        return "update";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public String delete(int id, HttpServletRequest request){
        bookMapper.delete(id);
        return "redirect:/list";
    }
}
