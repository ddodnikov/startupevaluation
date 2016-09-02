package com.theoryx.xseed.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.theoryx.xseed.annotation.Link;
import com.theoryx.xseed.dto.UrlLabelMapping;
import com.theoryx.xseed.dto.SnapshotDTO;
import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.service.SnapshotService;

@Controller
public class SnapshopController {

	@Autowired
	private SnapshotService snapshotService;

	/**
	 * This method shows all snapshots for current startup
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return "snapshots" String
	 */
	@Link(label = "Snapshots", parent = "Home", url = "/snapshots")
	@RequestMapping(value = "/snapshots", method = RequestMethod.GET)
	public String getAllSnapshots(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (request.getSession().getAttribute("currentUser") != null) {
			StartupDTO startupDTO = (StartupDTO) session.getAttribute("currentStartup");
			List<SnapshotDTO> snapshotsDTO = snapshotService.findAllSnapshotsByStartup(startupDTO);
			model.addAttribute("snapshots", snapshotsDTO);
			request.getAttribute("breadCrumbs");
			model.addAttribute("breadcrumbs", request.getAttribute("breadCrumbs"));
			return "/snapshots";
		} else {
			return "redirect:/login";
		}
	}

	/**
	 * This method shows details for the chosen snapshots
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param id String
	 * @return "snapshotdetails" String
	 */
	@Link(label = "<SnapshotName>", parent = "Snapshots", url = "/snapshot-details/<id>")
	@RequestMapping(value = "/snapshot-details/{id}", method = RequestMethod.GET)
	public String getSnapshotDetails(@PathVariable(value = "id") String id, Model model, HttpServletRequest request,
			HttpSession session) {
		if (request.getSession().getAttribute("currentUser") != null) {
			StartupDTO startupDTO = (StartupDTO) session.getAttribute("currentStartup");
			SnapshotDTO snapshotDTO = snapshotService.getByIdAndStartup(id, startupDTO);
			if(snapshotDTO == null){
				return "redirect:/403";
			}else{
				model.addAttribute("snapshot", snapshotDTO);
				@SuppressWarnings("unchecked")
				LinkedList<UrlLabelMapping> mappings = (LinkedList<UrlLabelMapping>) request.getAttribute("breadCrumbs");
				mappings.getLast().setLabel(snapshotDTO.getName());
				mappings.getLast().setUrl("/snapshot-details/"+snapshotDTO.getId());
				model.addAttribute("breadcrumbs", mappings);
				return "/snapshotdetails";
			}
		}else{
			return "redirect:/403";
		}
	}

}
