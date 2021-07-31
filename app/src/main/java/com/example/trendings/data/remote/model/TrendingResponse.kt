package com.example.trendings.data.remote.model

import com.example.trendings.data.local.models.Trending
import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    val totalCount: Int? = null,
    val incompleteResults: Boolean? = null,
    val items: List<Item>? = null
)

data class Owner(
    val gistsUrl: String? = null,
    val reposUrl: String? = null,
    val followingUrl: String? = null,
    val starredUrl: String? = null,
    val login: String? = null,
    val followersUrl: String? = null,
    val type: String? = null,
    val url: String? = null,
    val subscriptionsUrl: String? = null,
    val receivedEventsUrl: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val eventsUrl: String? = null,
    val htmlUrl: String? = null,
    val siteAdmin: Boolean? = null,
    val id: Int? = null,
    val gravatarId: String? = null,
    val nodeId: String? = null,
    val organizationsUrl: String? = null
)

data class Item(
    @SerializedName("stargazers_count")
    val stargazersCount: Int? = null,
    val pushedAt: String? = null,
    val subscriptionUrl: String? = null,
    val language: String? = null,
    val branchesUrl: String? = null,
    val issueCommentUrl: String? = null,
    val labelsUrl: String? = null,
    val score: Double? = null,
    val subscribersUrl: String? = null,
    val releasesUrl: String? = null,
    val svnUrl: String? = null,
    val id: Int? = null,
    val forks: Int? = null,
    val archiveUrl: String? = null,
    val gitRefsUrl: String? = null,
    val forksUrl: String? = null,
    val statusesUrl: String? = null,
    val sshUrl: String? = null,
    val license: License? = null,
    val fullName: String? = null,
    val size: Int? = null,
    val languagesUrl: String? = null,
    val htmlUrl: String? = null,
    val collaboratorsUrl: String? = null,
    val cloneUrl: String? = null,
    val name: String? = null,
    val pullsUrl: String? = null,
    val defaultBranch: String? = null,
    val hooksUrl: String? = null,
    val treesUrl: String? = null,
    val tagsUrl: String? = null,
    val jsonMemberPrivate: Boolean? = null,
    val contributorsUrl: String? = null,
    val hasDownloads: Boolean? = null,
    val notificationsUrl: String? = null,
    val openIssuesCount: Int? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val watchers: Int? = null,
    val keysUrl: String? = null,
    val deploymentsUrl: String? = null,
    val hasProjects: Boolean? = null,
    val archived: Boolean? = null,
    val hasWiki: Boolean? = null,
    val updatedAt: String? = null,
    val commentsUrl: String? = null,
    val stargazersUrl: String? = null,
    val disabled: Boolean? = null,
    val gitUrl: String? = null,
    val hasPages: Boolean? = null,
    val owner: Owner? = null,
    val commitsUrl: String? = null,
    val compareUrl: String? = null,
    val gitCommitsUrl: String? = null,
    val blobsUrl: String? = null,
    val gitTagsUrl: String? = null,
    val mergesUrl: String? = null,
    val downloadsUrl: String? = null,
    val hasIssues: Boolean? = null,
    val url: String? = null,
    val contentsUrl: String? = null,
    val mirrorUrl: Any? = null,
    val milestonesUrl: String? = null,
    val teamsUrl: String? = null,
    val fork: Boolean? = null,
    val issuesUrl: String? = null,
    val eventsUrl: String? = null,
    val issueEventsUrl: String? = null,
    val assigneesUrl: String? = null,
    val openIssues: Int? = null,
    val watchersCount: Int? = null,
    val nodeId: String? = null,
    val homepage: String? = null,
    val forksCount: Int? = null
)

/**
 * Converts response model list to local db model list.
 */
fun List<Item>.toLocalList(): MutableList<Trending> {
    return MutableList(size) { get(it).run {
            Trending(
                id = id,
                username = owner?.login,
                libraryName = name,
                language = language,
                description = description,
                imageUrl = owner?.avatarUrl,
                stars = stargazersCount
            )
        }
    }
}

data class License(
    val name: String? = null,
    val spdxId: String? = null,
    val key: String? = null,
    val url: Any? = null,
    val nodeId: String? = null
)
