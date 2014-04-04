jAutoUpdater
============

[![Build Status](https://travis-ci.org/JTWalraven/jAutoUpdater.png?branch=master)](https://travis-ci.org/JTWalraven/jAutoUpdater)

A Java based auto-updater following the Java standard of "Build once, run anywhere".

How it Works
____________

jAutoUpdate uses RSS feeds specialized for App updates called "AppFeeds". jAutoUpdate reads these feeds and checks for a new update. It then takes the info from the AppFeed and asks the user if they would wish to update. If the user responds yes to downloading, it downloads the update and creates a script, which replaces the old files (including the updater) and relaunches the application. The updater then deletes the script and the application is updated succesfully.

License
_______

   Copyright 2014 Jeffrey Walraven

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
