package furhatos.app.affectivereaction.setting

import furhatos.records.Location

val maxNumberOfUsers = 3
val distanceToEngage = 2.0
val location_LEFT = Location(0.8, 0.0, 1.0)
val location_RIGHT = Location(-0.9, 0.0, 1.0)
val location_FRONT = Location(0.0, 0.0, 2.0)
val listPositions = listOf(location_LEFT, location_RIGHT, location_FRONT)
const val p1 = "Participant 1"
const val p2 = "Participant 2"
const val p3 = "Participant 3"
var listParticipants = mutableListOf<String>()
var mapParticipants = mutableMapOf(
    location_LEFT to p1,
    location_FRONT to p2,
    location_RIGHT to p3)