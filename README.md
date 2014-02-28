jAutoUpdater
============

[![Build Status](https://travis-ci.org/JTWalraven/jAutoUpdater.png?branch=master)](https://travis-ci.org/JTWalraven/jAutoUpdater)

A Java based auto-updater following the Java standard of "Build once, run anywhere".

## How it Works
jAutoUpdate uses RSS feeds specialized for App updates called "AppFeeds". jAutoUpdate reads these feeds and checks for a new update. It then takes the info from the AppFeed and asks the user if they would wish to update. If the user responds yes to downloading, it downloads the update and creates a script, which replaces the old files (including the updater) and relaunches the application. The updater then deletes the script and the application is updated succesfully.
