package com.test.gojek.net.model


data class Repository(
     var author: String?=null,
     var avatar: String? ,
     var currentPeriodStars: Long = 0,
     var description: String? = null,
     var forks: Long = 0,
     var language: String?,
     var languageColor: String?,
     var name: String?,
     var stars: Long = 0,
     var url: String,
     var expanded:Boolean = false);
