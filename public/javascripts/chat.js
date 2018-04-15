var app = angular.module('chatApp', ['ngMaterial']);

app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('purple')
        .accentPalette('orange');
});

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