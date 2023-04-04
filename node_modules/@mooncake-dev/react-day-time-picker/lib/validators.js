"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.preventPastDays = preventPastDays;

/**
 * A validator function that prevents a user from picking past days.
 *
 * @param {Date} calendarDay - a calendar day, starting at "00:00:00" hours
 *
 * @return {Boolan} If the day can be picked by the user (valid) or not.
 */
function preventPastDays(calendarDay) {
  var now = new Date();
  var today = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 0, 0, 0);
  var isValid = calendarDay.getTime() >= today.getTime();
  return isValid;
}