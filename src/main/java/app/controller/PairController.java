package app.controller;

import app.model.Pair;
import app.model.User;
import app.util.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PairController {

    @Autowired
    private Scheduler scheduler;

    @GetMapping(path="/pair/generate")
    public @ResponseBody
    Pair getNewPair() {
        return scheduler.scheduledTask();
    }

}
