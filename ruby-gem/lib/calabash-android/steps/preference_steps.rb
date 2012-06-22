Given /^I set "([^\"]*)" to "([^\"]*)" in the "([^\"]*)" preferences file$/ do |key, value, pref|
  performAction('put_string_preference', pref, key, value)
end

Given /^I set boolean "([^\"]*)" to "(true|false)" in the "([^\"]*)" preferences file$/ do |key, value, pref|
  performAction('put_boolean_preference', pref, key, value)
end

Given /^I remove "([^\"]*)" from the "([^\"]*)" preferences file$/ do |key, pref|
  performAction('remove_preference', pref, key)
end

Given /^I clear the "([^\"]*)" preferences file$/ do |pref|
  performAction('clear_preferences', pref)
end


