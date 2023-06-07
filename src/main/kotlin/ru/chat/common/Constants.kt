package ru.chat.common

const val secret = "aVerySecretKey"
const val issuer = "chat.ru"
const val audience = "chat.ru"
const val mRealm = "Access to 'chat.ru'"

const val URL_DATABASE = "jdbc:postgresql://localhost:5432/chat-database"
const val DRIVER_DATABASE = "org.postgresql.Driver"
const val USER_DATABASE = "postgres"
const val PASSWORD_DATABASE = "761ihigoz"

const val ENDPOINT_SIGNUP = "/auth/signup"
const val ENDPOINT_LOGIN = "auth/login"
const val ENDPOINT_FRIEND_LIST = "/chat/friends-list"
const val ENDPOINT_CHAT_HISTORY = "/chat/chat-history"
const val ENDPOINT_CHAT_CONNECT = "/chat/connect"

val ERROR_USER_EXISTS: HashMap<String, String> = hashMapOf("message" to "User already exists.")
val ERROR_USER_NOT_EXISTS: HashMap<String, String> = hashMapOf("message" to "User doesn't exists.")
val ERROR_INVALID_CREDENTIALS: HashMap<String, String> = hashMapOf("message" to "Invalid credentials.")
val ERROR_MISSING_USERNAME: HashMap<String, String> = hashMapOf("message" to "Missing username.")
val ERROR_MISSING_EMAIL: HashMap<String, String> = hashMapOf("message" to "Missing email.")
val ERROR_MISSING_PASSWORD: HashMap<String, String> = hashMapOf("message" to "Missing password.")