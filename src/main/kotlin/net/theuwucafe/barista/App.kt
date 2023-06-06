/*
 * Copyright (c) 2023 Jade Lily Nash.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.theuwucafe.barista

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.Snowflake

val TEST_SERVER_ID = Snowflake(
	env("TEST_SERVER").toLong()  // Get the test server ID from the env vars or a .env file
)

private val TOKEN = env("TOKEN")   // Get the bot' token from the env vars or a .env file

suspend fun main() {
	val bot = ExtensibleBot(TOKEN) {
		chatCommands {
			defaultPrefix = "?"
			enabled = true

			prefix { default ->
				if (guildId == TEST_SERVER_ID) {
					// For the test server, we use ! as the command prefix
					"!"
				} else {
					// For other servers, we use the configured default prefix
					default
				}
			}
		}
	}

	bot.start()
}
