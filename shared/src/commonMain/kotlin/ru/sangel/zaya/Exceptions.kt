package ru.sangel.zaya

class UserNotFoundException() : NullPointerException("user_id")

class TokenNotFoundException() : NullPointerException()

class EmergencyNumberNotFoundException : NullPointerException("Emergency Number Not Found")

class LocationNotFoundException : NullPointerException("Location Not Found")