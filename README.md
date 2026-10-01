# Android Project 5 - Bitfit

Submitted by: **Nykehi Sanders**

**Bitfit**, Totally not Fitbit, is a health metrics app that allows users to track their exercise sessions by entering the name of their workout, workout duration, and calories burned. The app saves each workout locally and displays previous entries so users can keep track of their workout history and overall progress.

Time spent: **9** hours spent in total

## Required Features

The following **required** functionality is completed:

- [x] **At least one health metric is tracked (based on user input)**
  - Chosen metric(s): `Exercise sessions, workout duration, and calories burned`
- [x] **There is a "create entry" UI that prompts users to make their daily entry**
- [x] **New entries are saved in a database and then updated in the RecyclerView**
- [x] **On application restart, previously entered entries are preserved (i.e., are *persistent*)**
 
The following **optional** features are implemented:

- [x] **Create a UI for tracking averages and trends in metrics**
- [x] **Improve and customize the user interface through styling and coloring**

The following **additional** features are implemented:

- [x] Added a progress dashboard that displays the total number of workouts, average workout duration, average calories burned, and total calories burned.
- [x] Added input validation to prevent users from saving incomplete workout entries.
- [x] Added an empty-state message when the user has not entered any workouts yet.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img width="414" height="861" alt="BitFit Demo" src="https://github.com/user-attachments/assets/5abec83c-1c02-4233-b3fc-45dfe5ddc0c1" />

GIF created with **ScreenToGif**

## Notes

One challenge I encountered while completing this project was setting up the Room database and connecting the saved workout entries to the RecyclerView. I also ran into issues with some of the XML IDs not being recognized in my Kotlin files, which caused unresolved reference errors. After making sure the IDs in my layouts matched the IDs being referenced in my activities and rebuilding the project, I was able to get everything working correctly and perfect. This project also helped me gain more experience working with Room databases, DAOs, entities, RecyclerViews, and storing persistent data in an Android application. I also learned how to calculate information from saved database entries to create a progress dashboard showing workout averages and totals.

## License

    Copyright 2026 Nykehi Sanders

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
