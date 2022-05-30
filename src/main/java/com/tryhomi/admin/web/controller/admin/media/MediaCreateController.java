

package com.tryhomi.admin.web.controller.admin.media;

import com.tryhomi.admin.autoconfigure.WallRideProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.tryhomi.admin.domain.Media;
import com.tryhomi.admin.service.MediaService;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/{language}/media/create")
public class MediaCreateController {

	@Autowired
	private MediaService mediaService;
	//@Autowired
	private WallRideProperties wallRideProperties;

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody MediaCreatedModel create(@RequestParam MultipartFile file, HttpServletRequest request) {
		Media media = mediaService.createMedia(file);
		return new MediaCreatedModel(media, request.getContextPath(), wallRideProperties);
	}
}
