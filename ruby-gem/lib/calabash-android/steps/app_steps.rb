# -*- coding: utf-8 -*-
Given /^My app is running$/ do
#  rotate_phone(0)
end

Given /^my app is running$/ do
#  rotate_phone(0)
end

When /^I restart the activity$/ do
  performAction('restart_activity')
end

When /^I start the app$/ do
  performAction('restart_app')
end