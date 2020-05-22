package com.vishal.paytminsider.model

import com.google.gson.annotations.SerializedName

sealed class EventDto {

    /** This Wrapper Class is used to create a virtual response Hierarchy to only parse Master List **/
    data class ResponseWrapper(
        @SerializedName("list") val listWrapper: ListObj
    ) : EventDto() {
        data class ListObj(
            @SerializedName("masterList") val masterList: HashMap<String, Event>
        )
    }

    data class Event(
        @SerializedName("_id") val id: String,
        @SerializedName("min_show_start_time") val minShowStartTime: Int,
        @SerializedName("name") val name: String,
        @SerializedName("type") val type: String,
        @SerializedName("slug") val slug: String,
        @SerializedName("horizontal_cover_image") val horizontalCoverImage: String,
        @SerializedName("tags") val tags: List<Tag>,
        @SerializedName("city") val city: String,
        @SerializedName("venue_id") val venueId: String,
        @SerializedName("venue_name") val venueName: String,
        @SerializedName("venue_date_string") val venueDateString: String,
        @SerializedName("venue_geolocation") val venueGeolocation: VenueGeolocation,
        @SerializedName("is_rsvp") val isRsvp: Boolean,
        @SerializedName("category_id") val categoryId: CategoryId,
        @SerializedName("group_id") val groupId: GroupId,
        @SerializedName("event_state") val eventState: String,
        @SerializedName("price_display_string") val priceDisplayString: String,
        @SerializedName("communication_strategy") val communicationStrategy: String,
        @SerializedName("model") val model: String,
        @SerializedName("applicable_filters") val applicableFilters: List<String>,
        @SerializedName("popularity_score") val popularityScore: Double,
        @SerializedName("favStats") val favStats: FavStats,
        @SerializedName("min_price") val minPrice: Int
    ) : EventDto() {
        data class Tag(
            @SerializedName("is_featured") val isFeatured: Boolean,
            @SerializedName("is_carousel") val isCarousel: Boolean,
            @SerializedName("is_pick") val isPick: Boolean,
            @SerializedName("is_primary_interest") val isPrimaryInterest: Boolean,
            @SerializedName("_id") val id: String,
            @SerializedName("priority") val priority: Int,
            @SerializedName("tag_id") val tagId: String
        )

        data class VenueGeolocation(
            @SerializedName("latitude") val latitude: Double,
            @SerializedName("longitude") val longitude: Double
        )

        data class CategoryId(
            @SerializedName("_id") val id: String,
            @SerializedName("name") val name: String,
            @SerializedName("icon_img") val iconImg: String,
            @SerializedName("display_details") val displayDetails: DisplayDetails
        ) {
            data class DisplayDetails(
                @SerializedName("colour") val colour: String,
                @SerializedName("seo_title") val seoTitle: String,
                @SerializedName("seo_description") val seoDescription: String
            )
        }

        data class GroupId(
            @SerializedName("_id") val id: String,
            @SerializedName("name") val name: String,
            @SerializedName("icon_img") val iconImg: String,
            @SerializedName("display_details") val displayDetails: DisplayDetails
        ) {
            data class DisplayDetails(
                @SerializedName("colour") val colour: String,
                @SerializedName("seo_title") val seoTitle: String,
                @SerializedName("seo_description") val seoDescription: String
            )
        }

        data class FavStats(
            @SerializedName("target_id") val targetId: String,
            @SerializedName("actualCount") val actualCount: Int,
            @SerializedName("prettyCount") val prettyCount: Int
        )
    }
}