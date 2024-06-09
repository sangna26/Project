class Activity:
    def __init__(self,name,start_time,finish_time):

        #Initialize Activity object
        self.name=name
        self.st=start_time
        self.ft=finish_time
    def __str__(self):

        #String representation of the Activity object.
        return f'({self.name},{self.st},{self.ft})'
    def __gt__(self,o):

        #Comparison method to check if the finish time of this activity is greater than another.
        return self.ft>o.ft

#Sort the given list of activities using bubble sort algorithm.
def bubble_sort(arr):
    n=len(arr)
    for i in range(n):
        for j in range(n-i-1):
            if arr[j]>arr[j+1]:
                arr[j], arr[j+1]=arr[j+1], arr[j]
    return arr
    #Returns:
    #  list: Sorted list of Activity objects based on finish time.

# List of activities
activities=[Activity('A1',1,3),Activity('A2',2,5),
            Activity('A3',3,4),Activity('A4',4,7),
            Activity('A5',7,10),Activity('A6',8,9),
            Activity('A7',9,11),Activity('A8',9,13),
            Activity('A9',11,12),Activity('A10',12,14)]

# Sort activities in ascending order by finish time
print('Arrange in Ascending Order by Finish Time')
activities=bubble_sort(activities)
for i in activities:
    print(i)

# Select compatible activities
selected_activities=[activities[0]]
finish=activities[0].ft
print()
for i in range(1,len(activities)):
    start=activities[i].st
    if start>=finish:
        selected_activities.append(activities[i])
        finish=activities[i].ft

# Print selected activities
print('Selected Activities Are')
for i in selected_activities:
    print(i)
print()

# Print missed activities
print('Missed Activities Are')
for i in activities:
    if i not in selected_activities:
        print(i)