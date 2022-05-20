/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.netatmo.internal.handler.channelhelper;

import static org.openhab.binding.netatmo.internal.NetatmoBindingConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.netatmo.internal.api.data.EventType;
import org.openhab.binding.netatmo.internal.api.data.ModuleType;
import org.openhab.binding.netatmo.internal.api.dto.Event;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.types.State;

/**
 * The {@link EventPersonChannelHelper} handles specific channels of person events
 *
 * @author Gaël L'hopital - Initial contribution
 *
 */
@NonNullByDefault
public class EventPersonChannelHelper extends EventChannelHelper {
    public EventPersonChannelHelper() {
        super(GROUP_PERSON_EVENT);
    }

    @Override
    protected @Nullable State internalGetEvent(String channelId, Event event) {
        EventType eventType = event.getEventType();
        if (eventType.appliesOn(ModuleType.PERSON) && CHANNEL_PERSON_AT_HOME.equals(channelId)) {
            return OnOffType.from(EventType.PERSON.equals(eventType) || EventType.PERSON_HOME.equals(eventType));
        }
        return super.internalGetEvent(channelId, event);
    }
}
