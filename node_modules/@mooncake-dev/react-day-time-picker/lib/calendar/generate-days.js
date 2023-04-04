"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _dateFns = _interopRequireDefault(require("date-fns"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function generateDays(month) {
  var start = _dateFns["default"].startOfMonth(month);

  var end = _dateFns["default"].endOfMonth(month);

  var firstDay = _dateFns["default"].startOfWeek(start);

  var lastDay = _dateFns["default"].endOfWeek(end);

  var days = [];
  var day = firstDay;

  while (day <= lastDay) {
    days.push(day);
    day = _dateFns["default"].addDays(day, 1);
  }

  return [start, days];
}

var _default = generateDays;
exports["default"] = _default;