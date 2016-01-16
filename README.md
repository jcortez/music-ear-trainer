# music-ear-trainer
A music ear trainer web application that tests users on identifying generated chords.

## Current Status:
There is no GUI client yet. I am mostly working on implementing the server-side code at the moment. Custom mode is working (all of the appropriate REST web services are implemented).

REST web services implemented:
- GET /modes
- GET /modes/custom
- GET /modes/custom/question
- POST /modes/custom/answer
