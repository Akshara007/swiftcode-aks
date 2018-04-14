var app = angular.module('chatApp', ['ngMaterial']);

app.controller('chatController', function ($scope) {

    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'Hello'
		},
        {
            'sender': 'BOT',
            'text': 'Hi What can I do for u ???'
		},
        {
            'sender': 'USER',
            'text': 'Give me a serach result'
		},
        {
            'sender': 'BOT',
            'text': 'Ofcourse !!!'

			}

	];

});