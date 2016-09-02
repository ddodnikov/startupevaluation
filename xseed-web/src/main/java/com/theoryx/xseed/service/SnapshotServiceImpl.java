package com.theoryx.xseed.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.theoryx.xseed.dto.SnapshotDTO;
import com.theoryx.xseed.dto.SnapshotLineDTO;
import com.theoryx.xseed.dto.StartupDTO;
import com.theoryx.xseed.model.AnswerOption;
import com.theoryx.xseed.model.Question;
import com.theoryx.xseed.model.QuestionAnswerType;
import com.theoryx.xseed.model.Snapshot;
import com.theoryx.xseed.model.SnapshotLine;
import com.theoryx.xseed.model.Startup;
import com.theoryx.xseed.model.Survey;
import com.theoryx.xseed.model.User;
import com.theoryx.xseed.repository.SnapshotRepository;
import com.theoryx.xseed.utils.ValidationUtils;

@Service
public class SnapshotServiceImpl implements SnapshotService {

	@Autowired
	private SnapshotRepository snapshotRepository;
	@Autowired
	private StartupService startupService;
	@Autowired
	private UserService userService;
	@Autowired
	private SnapshotlineService snapshotlineService;

	@Override
	public List<SnapshotDTO> findAllSnapshotsByStartup(StartupDTO startupDTO) {
		Startup startup = startupService.convertStartupDTOToStartup(startupDTO);
		List<Snapshot> snapshots = snapshotRepository.findByStartupIdOrderByDateDesc(startup.getId());
		List<SnapshotDTO> snapshotsDTO = new ArrayList<SnapshotDTO>(snapshots.size());
		for (Snapshot snapshot : snapshots) {
			snapshotsDTO.add(convertSnapshotToLightSnapshotDTO(snapshot));
		}
		return snapshotsDTO;
	}

	@Override
	public List<Snapshot> findAllSnapshotsByStartupId(Integer startupId) {
		List<Snapshot> snapshots = snapshotRepository.findByStartupIdOrderByDateDesc(startupId);
		return snapshots;
	}

	public SnapshotDTO convertSnapshotToLightSnapshotDTO(Snapshot snapshot) {
		SnapshotDTO snapshotDTO = new SnapshotDTO();
		snapshotDTO.setId(snapshot.getId());
		snapshotDTO.setName(snapshot.getName());
		snapshotDTO.setDate(snapshot.getDate().toLocalDate());
		snapshotDTO.setUser(userService.convertUserToUserDTO(snapshot.getUser()));
		return snapshotDTO;
	}

	public SnapshotDTO convertSnapshotTOSnapshotDTO(Snapshot snapshot) {
		SnapshotDTO snapshotDTO = new SnapshotDTO();
		snapshotDTO.setId(snapshot.getId());
		snapshotDTO.setName(snapshot.getName());
		snapshotDTO.setDate(snapshot.getDate().toLocalDate());
		snapshotDTO.setStartup(startupService.convertStartupToStartupDTO(snapshot.getStartup()));
		snapshotDTO.setUser(userService.convertUserToUserDTO(snapshot.getUser()));
		List<SnapshotLineDTO> snapshotlineDTO = new ArrayList<SnapshotLineDTO>(snapshot.getSnapshotlines().size());
		for (SnapshotLine lines : snapshot.getSnapshotlines()) {
			snapshotlineDTO.add(snapshotlineService.convertSnapshotlineToSnapshotlineDTO(lines));
		}
		snapshotDTO.setSnapshotlines(snapshotlineDTO);
		return snapshotDTO;
	}

	@Override
	public SnapshotDTO getByIdAndStartup(String id, StartupDTO startupDTO) {
		Startup startup = startupService.convertStartupDTOToStartup(startupDTO);
		Snapshot snapshot = snapshotRepository.findById(Integer.valueOf(id));
		if (snapshot.getStartup().getId() != startup.getId()) {
			return null;
		} else {
			SnapshotDTO snapshotDTO = convertSnapshotTOSnapshotDTO(snapshot);
			return snapshotDTO;
		}

	}

	@Override
	public Snapshot saveSnapshot(Snapshot snapshot) {
		if (snapshot != null) {
			snapshot = snapshotRepository.save(snapshot);
		}
		return snapshot;
	}

	@Override
	public List<Snapshot> getAllLatestSnapshots() {
		List<Startup> startups = startupService.getAllStartups();
		List<Snapshot> snapshots = new ArrayList<Snapshot>();
		for (Startup startup : startups) {
			Snapshot s = getLatestSnapshot(startup);
			if (s != null)
				snapshots.add(s);
		}
		return snapshots;
	}

	@Override
	public Snapshot getLatestSnapshot(Startup startup) {
		return snapshotRepository.getLatestSnapshotByStartupId(startup.getId());
	}

	@Override
	public List<List<Snapshot>> groupSnapshots(List<Snapshot> filteredSnapshots) {
		List<List<Snapshot>> groups = new ArrayList<List<Snapshot>>();
		List<Integer> addedSnapshots = new ArrayList<Integer>();
		for (int snapShotIndex = 0; snapShotIndex < filteredSnapshots.size() - 1; snapShotIndex++) {
			Snapshot snapshot = filteredSnapshots.get(snapShotIndex);
			if (addedSnapshots.contains(snapshot.getId())) {
				continue;
			}
			List<SnapshotLine> snapshotLines = snapshotlineService.findAlgoSnapshotLinesBySnapshotId(snapshot.getId());
			addedSnapshots.add(snapshot.getId());
			List<Snapshot> group = new ArrayList<Snapshot>();
			groups.add(group);
			group.add(snapshot);
			for (int snapShotIndex2 = snapShotIndex + 1; snapShotIndex2 < filteredSnapshots.size(); snapShotIndex2++) {
				Snapshot snapshot2 = filteredSnapshots.get(snapShotIndex2);
				List<SnapshotLine> snapshotLines2 = snapshotlineService
						.findAlgoSnapshotLinesBySnapshotId(snapshot2.getId());
				boolean isTheSame = true;
				for (int snapShotLineIndex = 0; snapShotLineIndex < snapshotLines.size(); snapShotLineIndex++) {
					if (snapshotLines.get(snapShotLineIndex).getSelected_answer().getId() != snapshotLines2
							.get(snapShotLineIndex).getSelected_answer().getId()) {
						isTheSame = false;
						break;
					}
				}
				if (isTheSame == true) {
					group.add(snapshot2);
					addedSnapshots.add(snapshot2.getId());
				}
			}
		}
		return groups;
	}

	@Override
	public List<Snapshot> filterSnapshots(List<Question> filterQuestions, List<SnapshotLine> snapshotLines) {

		System.out.println("Filter questions : ");
		for (Question question : filterQuestions) {
			System.out.print(question.getText() + " , ");
		}
		System.out.println();

		System.out.println("Snapshotline from selected filters : ");
		for (SnapshotLine s : snapshotLines) {
			System.out.print(s.getId() + " : ");
			for (AnswerOption a : s.getMultipleSelectedAnswers()) {
				System.out.print(a.getText() + " , ");
			}
		}
		System.out.println();

		List<Snapshot> latestSnapshots = getAllLatestSnapshots();
		System.out.println("Latest snapshots : ");
		for (Snapshot s : latestSnapshots) {
			System.out.print(s.getId() + " , ");
		}
		System.out.println();

		List<Snapshot> filteredSnapshots = new ArrayList<Snapshot>();
		for (Snapshot snapshot : latestSnapshots) {
			System.out.println("Snapshot : " + snapshot.getId());
			boolean qualifies = true;
			for (Question question : filterQuestions) {
				System.out.println("Question : " + question.getText() + ", id = " + question.getId());
				SnapshotLine snapshotSnapshotLine = snapshotlineService.findByQuestionIdAndSnapshotId(question.getId(),
						snapshot.getId());
				if (snapshotSnapshotLine != null) {
					System.out.println("Snapshotline in database : " + snapshotSnapshotLine.getId());
					SnapshotLine snapshotLine = snapshotlineService.findByQuestionInList(snapshotLines,
							question.getId());
					List<Integer> ids = new ArrayList<Integer>();
					System.out.print("Answer option ids to search in : ");
					for (AnswerOption answerOption : Arrays.asList(snapshotLine.getMultipleSelectedAnswers())) {
						ids.add(answerOption.getId());
						System.out.print(answerOption.getId() + " , ");
					}
					System.out.println();
					if (question.getCategory().getType().toString()
							.equals(QuestionAnswerType.SINGLE_CHOICE.toString())) {
						System.out.print("Single question : ");
						AnswerOption givenAnswer = snapshotSnapshotLine.getSelected_answer();
						Integer id = givenAnswer.getId();
						System.out.print("Given answer option id : " + id);
						System.out.println();
						if (!ids.contains(id)) {
							System.out.println("Doesn't qualify");
							qualifies = false;
							break;
						}
					} else {
						System.out.println("Multiple question : ");
						System.out.print("Given answers : ");
						List<Integer> ids2 = new ArrayList<Integer>();
						for (AnswerOption answerOption : Arrays
								.asList((snapshotSnapshotLine.getMultipleSelectedAnswers()))) {
							ids2.add(answerOption.getId());
							System.out.print(answerOption.getId() + " , ");
						}
						System.out.println();
						if (!CollectionUtils.containsAny(ids, ids2)) {
							System.out.println("Doesn't qualify");
							qualifies = false;
							break;
						}
					}
				} else {
					System.out.println("No such snapshotline - Doesn't qualify");
					qualifies = false;
				}
			}
			if (qualifies == true) {
				System.out.println("Qualifies!!!");
				filteredSnapshots.add(snapshot);
			}
		}
		return filteredSnapshots;
	}

	@Override
	public Snapshot save(String name, Survey survey, User user, Startup startup, List<SnapshotLine> lines) {
		String snapshotName = ValidationUtils.validateSurveyName(name);
		Snapshot snapshot = new Snapshot();
		snapshot.setDate(LocalDateTime.now());
		snapshot.setUser(user);
		snapshot.setName(snapshotName);
		snapshot.setStartup(startup);
		snapshot.setSurvey(survey);

		snapshot = saveSnapshot(snapshot);
		for (SnapshotLine snapshotLine : lines) {
			snapshotLine.setSnapshot(snapshot);
		}
		snapshot.setSnapshotlines(lines);
		snapshot = saveSnapshot(snapshot);

		return saveSnapshot(snapshot);
	}

}
