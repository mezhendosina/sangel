package ru.sangel

class UserNotFoundException() : NullPointerException("user_id")

class TokenNotFoundException() : NullPointerException()

class EmergencyNumberNotFoundException : NullPointerException("Emergency Number Not Found")