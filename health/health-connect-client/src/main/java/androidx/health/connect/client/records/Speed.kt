/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package androidx.health.connect.client.records

import androidx.annotation.RestrictTo
import androidx.health.connect.client.aggregate.AggregateMetric
import androidx.health.connect.client.metadata.Metadata
import java.time.Instant
import java.time.ZoneOffset

/**
 * Captures the user's speed in meters per second. The value represents the scalar magnitude of the
 * speed, so negative values should not occur. Each record represents a single instantaneous
 * measurement.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public class Speed(
    /** Speed in meters per second. Required field. Valid range: 0-1000000. */
    public val speedMetersPerSecond: Double,
    override val time: Instant,
    override val zoneOffset: ZoneOffset?,
    override val metadata: Metadata = Metadata.EMPTY,
) : InstantaneousRecord {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Speed) return false

        if (speedMetersPerSecond != other.speedMetersPerSecond) return false
        if (time != other.time) return false
        if (zoneOffset != other.zoneOffset) return false
        if (metadata != other.metadata) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + speedMetersPerSecond.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + (zoneOffset?.hashCode() ?: 0)
        result = 31 * result + metadata.hashCode()
        return result
    }

    companion object {
        private const val SPEED_NAME = "Speed"
        private const val SPEED_FIELD_NAME = "speed"

        /**
         * Metric identifier to retrieve average speed from
         * [androidx.health.connect.client.aggregate.AggregationResult].
         */
        @JvmField
        val AVG: AggregateMetric<Double> =
            AggregateMetric.doubleMetric(
                SPEED_NAME,
                AggregateMetric.AggregationType.AVERAGE,
                SPEED_FIELD_NAME
            )

        /**
         * Metric identifier to retrieve minimum speed from
         * [androidx.health.connect.client.aggregate.AggregationResult].
         */
        @JvmField
        val MIN: AggregateMetric<Double> =
            AggregateMetric.doubleMetric(
                SPEED_NAME,
                AggregateMetric.AggregationType.MINIMUM,
                SPEED_FIELD_NAME
            )

        /**
         * Metric identifier to retrieve maximum speed from
         * [androidx.health.connect.client.aggregate.AggregationResult].
         */
        @JvmField
        val MAX: AggregateMetric<Double> =
            AggregateMetric.doubleMetric(
                SPEED_NAME,
                AggregateMetric.AggregationType.MAXIMUM,
                SPEED_FIELD_NAME
            )
    }
}
