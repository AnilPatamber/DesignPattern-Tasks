package com.casestudy;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CandidateStreamingOperations {

	public static void main(String[] args) {

		List<Candidate> candidatesList = InterviewRepository.getCandidateList();
		System.out.println("\nCandidates List\n" + candidatesList);

		// Task 1 : list candidate names from Pune city
		List<Candidate> puneCandidates = candidatesList.stream()
				.filter(candid -> candid.getCity().equalsIgnoreCase("Pune")).collect(Collectors.toList());
		System.out.println("\nList of Pune Candidates: " + puneCandidates);

		// Task 2 : list city and count of candidates per city
		Map<String, Long> cityWiseCandid = candidatesList.stream().map(candid -> candid.getCity())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("\nCandidate count per city:\n " + cityWiseCandid);

		// Task 3 : list technical expertise and count of candidates
		Map<String, Long> expertiseCandid = candidatesList.stream().map(candid -> candid.getTechnicalExpertise())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("\nCandidate count with technical expertise :\n " + expertiseCandid);

		// Task 4 : list fresher candidates
		List<Candidate> freshersCandid = candidatesList.stream().filter(candid -> (candid.getYearsOfExperience() == 0))
				.collect(Collectors.toList());
		System.out.println("\nFresher Candidate list\n" + freshersCandid);

		// Task 5 : listing candidates with highest experience
		List<Candidate> highestExperienceCandid = candidatesList.stream()
				.sorted((candid1, candid2) -> candid2.getYearsOfExperience() - candid1.getYearsOfExperience())
				.collect(Collectors.toList());

		System.out.println("\nSorted List of Candidates by Experience\n" + highestExperienceCandid);

		// task 6 : sort the candidates by city name
		List<Candidate> sortedCandidWithCity = candidatesList.stream()
				.sorted((candid1, candid2) -> candid1.getCity().compareTo(candid2.getCity()))
				.collect(Collectors.toList());
		System.out.println("\nSorted List of Candidates by City Name\n" + sortedCandidWithCity);

	}

}

