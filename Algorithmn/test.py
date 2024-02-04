class Solution:
    def searchRange(self, nums: list[int], target: int) -> list[int]:
        # 이진 탐색

        def searchStart(nums,target):
            start,end=0,len(nums)-1

            while start<=end:
                mid=(start+end)//2  # 정수 오버플로우 방지를 위해 (start+end)//2 대신 start + (end - start) // 2 사용 가능
                if nums[mid]<target:
                    start=mid+1
                else:
                    end=mid-1
            return start
        
        def searchEnd(nums,target):
            start,end=0,len(nums)-1

            while start<=end:
                mid=(start+end)//2  # 정수 오버플로우 방지를 위해 (start+end)//2 대신 start + (end - start) // 2 사용 가능
                if nums[mid]<=target:
                    start=mid+1
                else:
                    end=mid-1
            return end
        
        start= searchStart(nums,target)
        end =searchEnd(nums,target)
        return [start,end]
            
            
                
        
                
sol=Solution()
print(sol.searchRange([5,7,7,7,8,10],8))
