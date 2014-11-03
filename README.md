GhostHunter
===========
James Wang: jjw6wz@virginia.edu 703-966-3601 "Adolf Satan"
Andrew Coffee: ac7gd 703-909-4393 "Java"
Matthew Ledwith: mcl3st@virginia.edu 4803102744 "Lewis"
Ryan Aubrey :    rma7qb@virginia.edu 540-250-3750  "Big Daddy"


How to get Started
===================
Download git for your OS 
Click on "Clone to desktop" in the sidebar on the right
Open eclipse and do File>Import>Exisiting Android Project and navigate to the folder you just cloned
Check "Add to workspace" if you want 

How to commit and add changes using the Git GUI client
============================= 
Check the files you want to add to your commit 
Enter a message into the summary box, fill out the description if you want 
Press sync in the top right corner 

To pull changes you also press sync

How to commit and add changes using command line (Unix)
======================================================
cd into your cloned folder 
set up your remote using "git remote add origin https://github.com/jameswang14/GhostHunter" (only needs to be done once)
use "git add ." or "git add -A" to add all new files/changes
then use "git commit -m '(insert commit message here")' " to commit your changes
then use "git push origin [branch name (usually master)]" to push your changes

To pull changes just use "git pull origin master"
