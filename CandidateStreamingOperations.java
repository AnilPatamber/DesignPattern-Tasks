package com.casestudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CandidateStreamingOperations {

	public static void main(String[] args) {
		List<Candidate> candidatesList = InterviewRepository.getCandidateList();
		System.out.println(candidatesList);
		
		//Task 1 : list candidate names from Pune city
		List<Candidate> puneCandidates = candidatesList.stream()
										.filter(candid->candid.getCity().equalsIgnoreCase("Pune"))
										.collect(Collectors.toList());
		System.out.println("List of Pune Candidates: "+puneCandidates);
		
		//Task 2 : list city and count of candidates per city
		Map<String,Long> cityWiseCandid = candidatesList.stream()
										.map(candid->candid.getCity())
										.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println("Candidate count per city:\n "+cityWiseCandid);
		
		
	}

}
