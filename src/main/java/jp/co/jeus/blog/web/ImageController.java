package jp.co.jeus.blog.web;

import jp.co.jeus.blog.service.ImageLoadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

/**
 * Image Controller
 */
@Log4j2
@RequestMapping("image")
@Controller
public class ImageController {

    @Autowired
    private ImageLoadService imageLoadService;
    @Autowired
    private AsyncListenableTaskExecutor taskExecutor;

    /**
     * Load an image<br>
     * Processes image loading asynchronously.<br>
     *
     * @param fileName
     * @return
     */
    @RequestMapping("load")
    @ResponseBody
    public ListenableFuture<HttpEntity<byte[]>> getImage(@RequestParam("name") String fileName) {
        return taskExecutor.submitListenable(() -> imageLoadService.getImage(fileName));
    }

    /**
     * Load logo image<br>
     * Processes image loading asynchronously.<br>
     *
     * @param logo
     * @return
     */
    @RequestMapping("logo/{logo}")
    @ResponseBody
    public ListenableFuture<HttpEntity<byte[]>> getLogo(@PathVariable("logo") final String logo) {
        return taskExecutor.submitListenable(() -> imageLoadService.getLogo(logo));
    }

}
