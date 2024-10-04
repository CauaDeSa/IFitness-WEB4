package br.edu.ifsp.ifitness.model.util.activities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsp.ifitness.model.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.edu.ifsp.ifitness.model.Activity;
import br.edu.ifsp.ifitness.model.User;
import br.edu.ifsp.ifitness.model.dto.ActivityByType;
import br.edu.ifsp.ifitness.model.util.LocalDateTypeAdapter;

public class ActivitiesReader {

	public static List<Activity> read(){
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, 
						new LocalDateTypeAdapter())
				.create();
		List<Activity> activities = null;
		String path = Util.RESOURCES_PATH.getValue() + "/activities.json";
		
		try {
			File file = new File(path);
			if(file.exists()) {
				BufferedReader buffer = new BufferedReader(
						new FileReader(path));
				TypeToken<List<Activity>> type = 
						new TypeToken<List<Activity>>() {};
				activities = gson.fromJson(buffer, type);
				buffer.close();
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return activities;
	}
	
	public static List<Activity> readByUser(User user){
		List<Activity> activities = read();
		if(activities != null) {
			List<Activity> userActivities = new ArrayList<>();
			for(Activity a: activities) {
				if(a.getUser().getId() == user.getId()) {
					userActivities.add(a);
				}
			}
			return userActivities;
		}
		return null;
	}

	public static Activity findById(Long id) {
		List<Activity> activities = read();
		for(Activity a: activities) {
			if(a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public static List<Activity> getActivitiesByFilter(ActivityFilter filter) {
		  List<Activity> activities = readByUser(filter.getUser());
		  
		  if (filter.getType() != null) {
		    activities = activities.stream().filter(
		    		a -> a.getType() == filter.getType()).toList();
		  }

		  if (filter.getInitialDate() != null) {
		    activities = activities.stream().filter(
		    		a -> a.getDate().isAfter(filter.getInitialDate()
		    				.minusDays(1))).toList();
		  }

		  if (filter.getFinalDate() != null) {
		    activities = activities.stream().filter(
		    		a -> a.getDate().isBefore(filter.getFinalDate()
		    				.plusDays(1))).toList();
		  }

		  return activities;
	}
	
	public static List<ActivityByType> getActivitiesStatisticsByType(User user){
		List<Activity> activities = readByUser(user); 
		Map<String, Integer> activityCounts = new HashMap<>();

        for (Activity activity : activities) {
            String activityType = activity.getType().getDescription();
            if (!activityCounts.containsKey(activityType)) {
                activityCounts.put(activityType, 0);
            }
            int currentCount = activityCounts.get(activityType);
            activityCounts.put(activityType, currentCount + 1);
        }

        List<ActivityByType> activityTypeList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : activityCounts.entrySet()) {
            ActivityByType activityByType = new ActivityByType(entry.getKey(), entry.getValue());
            activityTypeList.add(activityByType);
        }

        return activityTypeList;
	}
}






