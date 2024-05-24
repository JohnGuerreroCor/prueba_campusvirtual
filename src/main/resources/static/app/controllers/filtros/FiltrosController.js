angular.module('filters.controller', [])	
.filter('removeSpaces', [function() {
    return function(string) {
        if (!angular.isString(string)) {
            return string;
        }
        return string.replace(/_/g, ' ');
    };
}])
.filter('addSpaces', [function() {
    return function(string) {
        if (!angular.isString(string)) {
            return string;
        }
        return string.replace(/([A-Z])/g, ' $1').trim()
    };
}]);