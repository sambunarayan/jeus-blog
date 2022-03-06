package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ImageLoadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@RequestMapping("image")
@Controller
public class ImageController {

    @Autowired
    private ImageLoadService imageLoadService;
    @Autowired
    private AsyncListenableTaskExecutor taskExecutor;

    @RequestMapping("load")
    @ResponseBody
    public ListenableFuture<HttpEntity<byte[]>> getImage(@RequestParam("name") String fileName) {
        return taskExecutor.submitListenable(() -> imageLoadService.getImage(fileName));
    }

    @RequestMapping("logo/{logo}")
    @ResponseBody
    public ListenableFuture<HttpEntity<byte[]>> getLogo(@PathVariable("logo") final String logo) {
        return taskExecutor.submitListenable(() -> imageLoadService.getLogo(logo));
    }
}
