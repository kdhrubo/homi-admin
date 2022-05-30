

package com.tryhomi.admin.web.controller.admin.media;

import com.tryhomi.admin.autoconfigure.WallRideProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tryhomi.admin.domain.Media;
import com.tryhomi.admin.service.MediaService;


import java.util.List;

@Controller
@RequestMapping("/{language}/media/index")
public class MediaIndexController {

	@Autowired
	private MediaService mediaService;
	//@Autowired
	private WallRideProperties wallRideProperties;

	@RequestMapping
	public @ResponseBody MediaIndexModel[] index() {
		List<Media> medias = mediaService.getAllMedias();
		MediaIndexModel[] models = new MediaIndexModel[medias.size()];
		for (int i = 0; i < medias.size(); i++) {
			models[i] = new MediaIndexModel(medias.get(i), wallRideProperties);
		}
		return models;
	}
}
