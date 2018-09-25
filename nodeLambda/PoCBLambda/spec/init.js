let Jasmine = require('jasmine');
let jasmine = new Jasmine();

jasmine.loadConfigFile('spec/support/jasmine.json');
jasmine.configureDefaultReporter({
    showColors: false
});
jasmine.execute();