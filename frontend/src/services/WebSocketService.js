import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

let stompClient = null;

export const connectWebSocket = (
    onNotificationReceived,
    onAnalyticsUpdate
) => {

    const socket =
        new SockJS(
            'http://localhost:8083/ws'
        );

    stompClient =
        new Client({

            webSocketFactory: () => socket,

            reconnectDelay: 5000,

            onConnect: () => {

                console.log(
                    'WebSocket Connected'
                );

                stompClient.subscribe(
                    '/topic/notifications',
                    (message) => {

                        if (
                            onNotificationReceived
                        ) {

                            onNotificationReceived(
                                message.body
                            );
                        }
                    }
                );

                stompClient.subscribe(
                    '/topic/analytics',
                    (message) => {

                        if (
                            onAnalyticsUpdate
                        ) {

                            const data =
                                JSON.parse(
                                    message.body
                                );

                            onAnalyticsUpdate(
                                data
                            );
                        }
                    }
                );
            },

            onStompError: (frame) => {

                console.error(
                    'Broker error:',
                    frame.headers['message']
                );

                console.error(
                    'Details:',
                    frame.body
                );
            }
        });

    stompClient.activate();
};

export const disconnectWebSocket = () => {

    if (stompClient) {

        stompClient.deactivate();

        console.log(
            'WebSocket Disconnected'
        );
    }
};