package org.example.springsecuritypractice.controller;

import org.example.springsecuritypractice.utils.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping("/")
public class InitController {

    @GetMapping("health")
    public HttpResponse<String> health() {
        return new HttpResponse<>(HttpStatus.OK, "Up and running");
    }

    @GetMapping(path = "/license", produces = "text/html")
    public String license() {
        var year = Calendar.getInstance().get(Calendar.YEAR);
        return STR."""
                <h1>MIT License</h1>
                <br>
                <strong>Copyright (c) \{year} Luigi Vismara</strong>
                <br>
                <br>
                Permission is hereby granted, free of charge, to any person obtaining a copy
                of this software and associated documentation files (the "Software"), to deal
                in the Software without restriction, including without limitation the rights
                to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
                copies of the Software, and to permit persons to whom the Software is
                furnished to do so, subject to the following conditions:
                <br>
                <br>
                The above copyright notice and this permission notice shall be included in all
                copies or substantial portions of the Software.
                <br>
                <br>
                THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
                IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
                FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
                AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
                LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
                OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
                SOFTWARE.
                """;
    }

}
